import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import { GET_UNIVERSITY, DELETE_UNIVERSITY } from "./universityActionTypes";
import Swal from "sweetalert2";
import { GET_UNAUTHENTICATED } from "../Authenticated/authenticatedType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 2500,
});

export const getUniversity = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/universities`, {
      headers: authHeader(),
    });
    dp({
      type: GET_UNIVERSITY,
      university: result.data.data,
    });
  };
};
export const addUniversity = (university, cb) => {
  axios
    .post(`${baseURI}/universities`, university, {
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
export const editUniversity = (id, university, cb) => {
  axios
    .put(`${baseURI}/universities/${id}`, university, {
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

export const deleteUniversity = (id) => {
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
          .delete(`${baseURI}/universities/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_UNIVERSITY,
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
