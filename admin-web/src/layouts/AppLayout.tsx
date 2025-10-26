import { Button } from '@/components/ui/button'
import { getKeycloak } from '@/features/keycloak/keycloak'
import React from 'react'
import { Outlet } from 'react-router-dom'

const AppLayout = () => {
  return (
    <div>AppLayout
      <Button
        onClick={() => {
          const keycloak = getKeycloak();
          keycloak.login();
        }}
      >Login</Button>
      <Button
        onClick={() => {
          const keycloak = getKeycloak();
          const confirmLogout = window.confirm("Bạn có chắc muốn đăng xuất không?");
          if (confirmLogout) {
            keycloak.logout();
          }
        }}
      >
        Logout
      </Button>
      <Outlet />

      <Outlet />

      <Button
        onClick={() => {
          const keycloak = getKeycloak();
          keycloak.accountManagement();
          // keycloak.login();
        }}
      >Security</Button>
      <Outlet />
    </div>
  )
}

export default AppLayout