import axios from "axios";
import authHeader from "../../Login/securityUtils/authHeader";
import {
  GET_PROJECT,
  DELETE_PROJECT,
  GET_PROJECT_ONE,
} from "../Project/projectActionType";
import { baseURI } from "../../../../baseURL/baseURL";
import Swal from "sweetalert2";
import { GET_UNAUTHENTICATED } from "../../Authenticated/authenticatedType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

//Get all project
export const getProject = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/projects`, {
      headers: authHeader(),
    });
    dp({
      type: GET_PROJECT,
      project: result.data.data,
    });
  };
};

export const getProjectOne = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/projects/${id}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_PROJECT_ONE,
      projectOne: result.data.data,
    });
  };
};

export const getProjectById = (id, cb) => {
  axios
    .get(`${baseURI}/projects/${id}`, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res.data.data);
    });
};

//Delete Project
export const deleteProject = (id) => {
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
          .delete(`${baseURI}/projects/${id}`, { headers: authHeader() })
          .then((res) => {
            dp({
              type: DELETE_PROJECT,
              payLoad: id,
            });
            Toast.fire({
              icon: "success",
              title: "has been deleted successfully",
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

//Add Project
export const addProject = (project, cb) => {
  axios
    .post(`${baseURI}/projects`, project, { headers: authHeader() })
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

//Update Project
export const updateProject = (id, project, cb) => {
  axios
    .put(`${baseURI}/projects/${id}`, project, { headers: authHeader() })
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
