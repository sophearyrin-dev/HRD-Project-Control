import axios from "axios";
import { baseURI } from "../../../../baseURL/baseURL";
import authHeader from "../../Login/securityUtils/authHeader";
import Swal from "sweetalert2";
import {Redirect } from "react-router-dom";

import {GET_UNAUTHENTICATED} from '../../Authenticated/authenticatedType';

import {
  DELETE_PROJECTSCHEDULE,
  DELETE_PROJECTSCHEDULEONE,
  GET_PROJECTSCHEDULE,
  GET_PROJECTSCHEDULEONE,
  GET_PROJECT_SCHEDULE_USER,
} from "./projectScheduleType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3500,
});
export const getProjectSchedule = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/project-schedule`, {
      headers: authHeader(),
    });
    dp({
      type: GET_PROJECTSCHEDULE,
      projectSchedule: result.data.data,
    });
  };
};

export const updateProjectSchedule = (id, schedule, cb) => {
  axios
    .put(`${baseURI}/project-schedule/${id}`, schedule, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    }).catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};

export const deleteProjectSchedule = (id) => {
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
          .delete(`${baseURI}/project-schedule/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_PROJECTSCHEDULE,
              payLoad: id,
            });
            Toast.fire({
              icon: "success",
              title: "has been deleted successfuly",
            });
          }).catch(function (error) {
            dp({
              type: GET_UNAUTHENTICATED,
              payLoad: false,
            });
          });
      }
    });
  };
};

export const getProjectScheduleOne = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/project-schedule-topic/${id}`, {
      headers: authHeader(),
    });

    dp({
      type: GET_PROJECTSCHEDULEONE,
      projectScheduleOne: result.data.data,
    });
  };
};

export const updateProjectScheduleOne = (id, schedule, cb) => {
  axios
    .put(`${baseURI}/project-schedule-topic/${id}`, schedule, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    }).catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};
// .put(`${baseURI}/document-schedule/${id}`, schedule, {
export const updateProjectStatus = (id, status, cb) => {
  axios
    .put(`${baseURI}/project-schedule/status/${id}`, status, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    }).catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    }).catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};

export const deleteProjectScheduleOne = (id) => {
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
          .delete(`${baseURI}/project-schedule-topic/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_PROJECTSCHEDULEONE,
              payLoad: id,
            });
            Toast.fire({
              icon: "success",
              title: "has been deleted successfuly",
            });
          }).catch(function (error) {
            dp({
              type: GET_UNAUTHENTICATED,
              payLoad: false,
            });
          });
      }
    });
  };
};

export const addProjectTopic = (tp, cb) => {
  axios
    .post(`${baseURI}/project-schedule-insert-all`, tp, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    }).catch(function (error) {
      cb({
        type: GET_UNAUTHENTICATED,
        payLoad: false,
      });
    });
};

export const getProjectScheduleUser = (id) => {
  return async (db) => {
    const result = await axios.get(`${baseURI}/project-schedule/${id}`, {
      headers: authHeader(),
    });
    db({
      type: GET_PROJECT_SCHEDULE_USER,
      projectScheduleUser: result.data.data,
    });
  };
};
