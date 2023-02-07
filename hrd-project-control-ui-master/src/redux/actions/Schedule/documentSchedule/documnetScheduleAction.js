import axios from "axios";
import { baseURI } from "../../../../baseURL/baseURL";
import authHeader from "../../Login/securityUtils/authHeader";
import Swal from "sweetalert2";
import {
  GET_DOCUMENTSCHEDULE,
  DELETE_DOCUMENTSCHEDULE,
  GET_DOCUMENTSCHEDULEONE,
  DELETE_DOCUMENTSCHEDULEONE,
  GET_DOCUMENT_SCHEDULE_USER,
} from "./documentScheduleType";

import { GET_UNAUTHENTICATED } from "../../Authenticated/authenticatedType";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3500,
});
export const getDocumentSchedule = () => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/document-schedule`, {
      headers: authHeader(),
    });
    dp({
      type: GET_DOCUMENTSCHEDULE,
      documentSchedule: result.data.data,
    });
  };
};

export const updateDocumentSchedule = (id, schedule, cb) => {
  axios
    .put(`${baseURI}/document-schedule/${id}`, schedule, {
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
  // return async (dp) => {
  //   const result = await axios.get(`${baseURI}/document-schedule`, {
  //     headers: authHeader(),
  //   });
  // };
};

export const deleteDocumnetSchedule = (id) => {
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
          .delete(`${baseURI}/document-schedule/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_DOCUMENTSCHEDULE,
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

export const getDocumentScheduleOne = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/document-schedule-topic/${id}`, {
      headers: authHeader(),
    });

    dp({
      type: GET_DOCUMENTSCHEDULEONE,
      documentScheduleOne: result.data.data,
    });
  };
};

export const getDocumentScheduleUser = (id) => {
  return async (db) => {
    const result = await axios.get(`${baseURI}/document-schedule/${id}`, {
      headers: authHeader(),
    });
    db({
      type: GET_DOCUMENT_SCHEDULE_USER,
      documentScheduleUser: result.data.data,
    });
  };
};

export const updateDocumentScheduleOne = (id, schedule, cb) => {
  axios
    .put(`${baseURI}/document-schedule-topic/${id}`, schedule, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    });
};
// .put(`${baseURI}/document-schedule/${id}`, schedule, {
export const updateDocumentStatus = (id, status, cb) => {
  axios
    .put(`${baseURI}/document-schedule/status/${id}`, status, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    });
};

export const deleteDocumnetScheduleOne = (id) => {
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
          .delete(`${baseURI}/document-schedule-topic/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_DOCUMENTSCHEDULEONE,
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

export const addDocumenetTopic = (tp, cb) => {
  axios
    .post(`${baseURI}/document-schedule-insert-all`, tp, {
      headers: authHeader(),
    })
    .then((res) => {
      cb(res);
    });
};
