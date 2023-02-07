import axios from "axios";
import authHeader from "../../Login/securityUtils/authHeader";
import { GET_PROJECTTYPE, DELETE_PROJECTTYPE } from "./scheduleTypeActionTypes";
import { baseURI } from "../../../../baseURL/baseURL";
import Swal from "sweetalert2";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

export const getProjectType = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/schedule-type`, {
      headers: authHeader(),
    });
    dp({
      type: GET_PROJECTTYPE,
      projectType: result.data.data,
    });
  };
};

export const addScheduleType = (category, cb) => {
  axios
    .post(`${baseURI}/schedule-type`, category, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    });
};

export const updateScheduleType = (id, category, cb) => {
  axios
    .put(`${baseURI}/schedule-type/${id}`, category, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    });
};

export const deleteScheduleType = (id) => {
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
          .delete(`${baseURI}/schedule-type/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_PROJECTTYPE,
              payLoad: id,
            });
          });
        Toast.fire({
          icon: "success",
          title: "has been deleted successfuly",
        });
      }
    });
  };
};
