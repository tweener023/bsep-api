import React from "react";
import { Route } from "react-router-dom";
import { Navigate } from "react-router-dom";


const ProtectedRoute = ({
  component: Component,
  roles,
  currentUser,
  ...rest
}) => {
  return (
    <Route
      {...rest}
      render={(props) => {
        if (!currentUser) {
          // User is not logged in, redirect to login page
          return <Navigate to="/login" />;
        }

        if (!roles.includes(currentUser.role)) {
          // User does not have the required role, redirect to unauthorized page
          return <Navigate to="/unauthorized" />;
        }

        // User has the required role, render the component
        return <Component {...props} />;
      }}
    />
  );
};

export default ProtectedRoute;
