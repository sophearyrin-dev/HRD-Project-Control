import React, { Component } from "react";
import { logout } from "../redux/actions/Login/loginAction";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import Avatar from "react-avatar";
import {
  CDropdown,
  CDropdownItem,
  CDropdownMenu,
  CDropdownToggle,
} from "@coreui/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons";
import localStorage from "../redux/actions/Login/securityUtils/localStorages";

class TheHeaderDropdown extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user: localStorage(),
    };
  }
  logOut(e) {
    e.preventDefault();
    this.props.logout();
    if (typeof window !== "undefined") {
      window.location.href = "/";
    }
  }

  render() {
    return (
      <CDropdown inNav className="c-header-nav-items mx-2" direction="down">
        <CDropdownToggle className="c-header-nav-link" caret={false}>
          <div className="c-avatar">
            <Avatar
              name={this.state.user.name}
              round
              size="40"
              maxInitials={2}
              textSizeRatio={2}
            />
          </div>
        </CDropdownToggle>
        <CDropdownMenu className="pt-0" placement="bottom-end">
          <CDropdownItem header tag="div" color="light" className="text-center">
            <strong>Profile</strong>
          </CDropdownItem>
          <CDropdownItem>
            <strong>NAME : {this.state.user.name.toUpperCase()}</strong>
          </CDropdownItem>
          <CDropdownItem>
            <strong>ROLE : {this.state.user.role}</strong>
          </CDropdownItem>

          <CDropdownItem divider />
          <CDropdownItem to="/" onClick={this.logOut.bind(this)}>
            <FontAwesomeIcon icon={faSignOutAlt} /> &nbsp; &nbsp;{" "}
            <strong>LOGOUT</strong>
          </CDropdownItem>
        </CDropdownMenu>
      </CDropdown>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    // users: state.loginReducer.users
  };
};

const mapDispatchToProps = (dispatch) => {
  return bindActionCreators(
    {
      logout,
    },
    dispatch
  );
};
export default connect(mapStateToProps, mapDispatchToProps)(TheHeaderDropdown);
