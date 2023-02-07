import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import { GET_GENERATION, DELETE_GENERATION } from "./generationActionTypes";
import Swal from "sweetalert2";
import { GET_UNAUTHENTICATED } from "../Authenticated/authenticatedType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 2500,
});

export const getGeneration = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/generations`, {
      headers: authHeader(),
    });
    dp({
      type: GET_GENERATION,
      generation: result.data.data,
    });
  };
};
export const addGeneration = (generation, cb) => {
  axios
    .post(`${baseURI}/generations`, generation, {
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
export const editGeneration = (id, generation, cb) => {
  axios
    .put(`${baseURI}/generations/${id}`, generation, {
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
export const deleteGeneration = (id) => {
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
          .delete(`${baseURI}/generations/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_GENERATION,
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
