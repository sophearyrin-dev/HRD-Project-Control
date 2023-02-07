import React, { Component } from "react";
import { Route, Switch, HashRouter } from "react-router-dom";
import "./scss/style.scss";
import "./css/default.css";

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse"></div>
  </div>
);

// Containers
const TheLayout = React.lazy(() => import("./containers/TheLayout"));

// Pages
const Login = React.lazy(() => import("./views/login/Login"));
const Page403 = React.lazy(() => import("./views/page_not_found/403"));

class App extends Component {
  render() {
    return (
      <HashRouter>
        <React.Suspense fallback={loading}>
          <Switch>
            <Route
              exact
              path="/login"
              name="Login Page"
              render={(props) => <Login {...props} />}
            />

            <Route
              exact
              path="/403"
              name="Page 403"
              render={(props) => <Page403 {...props} />}
            />

            <Route
              path="/"
              name="Home"
              render={(props) => <TheLayout {...props} />}
            />
          </Switch>
        </React.Suspense>
      </HashRouter>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    security: state.loginReducer.validToken,
  };
};

const mapDispatchToProps = (dispatch) => {};

export default App;
