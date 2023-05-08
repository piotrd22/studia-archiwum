import { useKeycloak } from "@react-keycloak/web";

const AdminRoute = ({ children }) => {
  const { keycloak } = useKeycloak();

  const isLoggedIn = keycloak.authenticated && keycloak.hasRealmRole("admin");

  return isLoggedIn ? children : null;
};

export default AdminRoute;
