import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import { GET_USER, DELETE_USER, GET_USER_ONE } from "./usercontrolActionTypes";
import Swal from "sweetalert2";
import { GET_UNAUTHENTICATED } from "../Authenticated/authenticatedType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3500,
});

export const getUser = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/users`, {
      headers: authHeader(),
    });
    dp({
      type: GET_USER,
      user: result.data.data,
    });
  };
};
export const getUserOne = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/users/${id}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_USER_ONE,
      userOne: result.data.data,
    });
  };
};

export const getUserById = (id, cb) => {
  axios
    .get(`${baseURI}/users/${id}`, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res.data.data);
    });
};

export const addUser = (user, cb) => {
  axios
    .post(`${baseURI}/users`, user, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    })
    .catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};

export const editUser = (id, user, cb) => {
  axios
    .put(`${baseURI}/users/${id}`, user, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    })
    .catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};

export const deleteUser = (id) => {
  return (dp) => {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      showCancelButton: true,
      confirmButtonColor: "#3c4b64",
      cancelButtonColor: "#e55353",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.value) {
        axios
          .delete(`${baseURI}/users/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_USER,
              payLoad: id,
            });
            Toast.fire({
              icon: "success",
              title: "has been deleted successfuly",
            });
          })
          .catch(function (error) {
            dp({
              type: GET_UNAUTHENTICATED,
              payLoad: false,
            });
          });
      }
    });
  };
};
