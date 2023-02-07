import axios from "axios";
import { POST_USERLOGIN, USER_LOGOUT } from "./loginActionTypes";
import localStorages from "./securityUtils/localStorages"; //global variable

var CryptoJS = require("crypto-js");
export const userLogin = (LoginRequest) => async (dispatch) => {
  const result = "";
  try {
    // post => Login Request
    const result = await axios.post(
      "http://35.209.148.231:1502/api/v1/users/authenticate",
      LoginRequest
    );
    // const {token} =result.data;
    if (result.data.data.data.jwtToken) {
      let encrypt = CryptoJS.AES.encrypt(
        result.data.data.data.jwtToken,
        "hrd-project-control"
      );
      localStorage.setItem(
        "user",
        JSON.stringify({
          id: result.data.data.userId,
          name: result.data.data.name,
          role: result.data.data.role[0].name,
          jwtToken: encrypt.toString(),
        })
      );
      localStorages(result.data.data);
    }
    dispatch({
      type: POST_USERLOGIN,
      payload: result.data,
    });
  } catch (err) {
    dispatch({
      type: POST_USERLOGIN,
      payload: result.data,
    });
  }
};

export const logout = () => async (dispatch) => {
  localStorage.removeItem("user");
  dispatch({
    type: USER_LOGOUT,
    payload: {},
  });
};
