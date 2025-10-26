import AuthProvider from "@/features/keycloak/providers/AuthProvider";

import AppLayout from "@/layouts/AppLayout";
import { createBrowserRouter } from "react-router-dom";


const router = createBrowserRouter([
  {
    element: <AuthProvider />,
    children: [
      {
        element: <AppLayout />, // layout bọc các route con
        children: [
          {
            path: "/",
            element: <div>Home</div>,
          },
          {
            path: "/profile",
            element: <div>Profile</div>,
          },
        ],
      },
    ],
  },
]);

export default router;
