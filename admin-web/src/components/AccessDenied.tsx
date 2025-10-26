import { Button } from '@/components/ui/button'
import { Card, CardContent, CardFooter, CardHeader } from '@/components/ui/card'
import KeycloakClient from '@/features/keycloak/keycloak';

import { useAuth } from '@/features/keycloak/providers/AuthProvider'


const AccessDenied = () => {
  const { profile, setProfile, setLoading } = useAuth();
  return (
    <div className='flex h-screen w-full items-center justify-center'>
        <Card className='h-max w-[600px]'>
            <CardHeader className='font-bold text-red-500 text-2xl'>Access Denied</CardHeader>
            {/* ep logout */}
            <CardContent>
                <p>Hello, {profile?.username}</p>

                <p className='text-gray-500 mt-4'>You do not have permission to access this page. 
                Please logout and login with an account that has the necessary permissions.
            </p>
            </CardContent>
            <CardFooter>
                <Button
                className='w-full'
                
                onClick={() => {
                  const keycloak = KeycloakClient.getInstance().keycloak;
                   keycloak.logout({
                      // redirectUri: window.location.origin,
                    });
                }}
                >Logout</Button>
            </CardFooter>
        </Card>
    </div>
  )
}

export default AccessDenied