import type { IUserProfile } from "@/types";
import { createContext, useContext, useEffect, useState } from "react";
import { getKeycloak } from "../keycloak";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import FullScreenSpinner from "@/components/FullScreenSpinner";
import AccessDenied from "@/components/AccessDenied";

type AuthContextType = {
  profile: IUserProfile | null;
  setProfile: (profile: IUserProfile | null) => void;
  setLoading: (loading: boolean) => void;
};

const AuthContext = createContext<AuthContextType>({
  profile: null,
  setProfile: () => {},
  setLoading: () => {},
});


let initFlag = false; // 🔹 fix double render



const AuthProvider = () => {
  const [profile, setProfile] = useState<IUserProfile | null>(null);
  const [loading, setLoading] = useState(true);
  const location = useLocation();
  const init = async () => {
    if (initFlag) return;
    initFlag = true;
    console.log("Start keycloak...")
    const kecloak = getKeycloak();
    
    
    const authenticated = await kecloak.init({
      onLoad: "check-sso",
      pkceMethod: "S256",
      silentCheckSsoRedirectUri:
        window.location.origin + "/silent-check-sso.html",
    });
    if (authenticated) {
      const roles = kecloak.realmAccess?.roles || [];
      const userProfile = await kecloak.loadUserProfile();
      const token = kecloak.token || "";
      setProfile({
        ...userProfile,
        roles,
        token,
      });

      console.log("User authenticated");
    }else{
      console.log("User not authenticated");
      kecloak.login();
    }
    setLoading(false);
  
  }


  useEffect(() => {
    init();
  }, []);


  if (loading || !profile) {
    return <FullScreenSpinner label="Loading Information..." />;
  }

  if (!loading && profile && !profile?.roles.includes("admin")) {
    return <AccessDenied />;
  }
 



  return (
    <AuthContext.Provider
      value={{
        profile,
        setProfile,
        setLoading,
      }}
    >
      <Outlet />
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);

export default AuthProvider;