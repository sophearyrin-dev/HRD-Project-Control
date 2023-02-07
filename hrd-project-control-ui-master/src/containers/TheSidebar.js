import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  CCreateElement,
  CSidebar,
  CSidebarBrand,
  CSidebarNav,
  CSidebarNavDivider,
  CSidebarNavTitle,
  CSidebarMinimizer,
  CSidebarNavDropdown,
  CSidebarNavItem,
} from "@coreui/react";

import logo from "../img/logo.png";
import logo_coolapse from "../img/logo-collapse.png";

import CIcon from "@coreui/icons-react";

// sidebar nav config
import navigation from "./_nav";

// user sidebar nav config
import navigation_user from "./user_nav";

import localStorage from "../redux/actions/Login/securityUtils/localStorages"; //global variable

const TheSidebar = () => {
  const dispatch = useDispatch();
  const show = useSelector((state) => state.changeStateReducer.sidebarShow);
  const user = localStorage();
  return (
    <CSidebar
      show={show}
      onShowChange={(val) => dispatch({ type: "set", sidebarShow: val })}
    >
      <CSidebarBrand className="d-md-down-none" to="/">
        <CIcon className="c-sidebar-brand-full" src={logo} height={35} />
        <CIcon
          className="c-sidebar-brand-minimized"
          src={logo_coolapse}
          height={35}
        />
      </CSidebarBrand>
      <CSidebarNav>
        {user.role === "SUPER_MENTOR" ? (
          <CCreateElement
            items={navigation}
            components={{
              CSidebarNavDivider,
              CSidebarNavDropdown,
              CSidebarNavItem,
              CSidebarNavTitle,
            }}
          />
        ) : (
          <CCreateElement
            items={navigation_user}
            components={{
              CSidebarNavDivider,
              CSidebarNavDropdown,
              CSidebarNavItem,
              CSidebarNavTitle,
            }}
          />
        )}
      </CSidebarNav>
      <CSidebarMinimizer className="c-d-md-down-none" />
    </CSidebar>
  );
};

export default React.memo(TheSidebar);
