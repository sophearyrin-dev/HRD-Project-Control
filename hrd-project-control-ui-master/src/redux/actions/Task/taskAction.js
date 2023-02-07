import axios from "axios";
import { baseURI } from "../../../baseURL/baseURL";
import authHeader from "../Login/securityUtils/authHeader";
import {
  GET_TASK,
  GET_TASKVIEW,
  GET_MYTASK,
  GET_ALLTASK,
  GET_SUBTASK,
  GET_SUBTASK_BY_MAIN,
  DELETE_SUBTASK,
  GET_USER_PRO_ID,
  GET_ISSUE_BY_MAIN,
} from "../../actions/Task/taskActionTypes";
import { GET_UNAUTHENTICATED } from "../../actions/Authenticated/authenticatedType";

import Swal from "sweetalert2";

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3500,
});

export const getTask = (courseId, generationId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/tasks/all-project-progress?courseId=${courseId}&generationId=${generationId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_TASK,
      task: result.data.data,
    });
  };
};

export const getTaskview = (pId) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/tasks?projectId=${pId}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_TASKVIEW,
      taskview: result.data.data,
    });
  };
};

export const getMyTask = (userid, projectid) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/subtasks?handlerId=${userid}&projectId=${projectid}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_MYTASK,
      mytask: result.data.data,
    });
  };
};

export const getAllTask = (projectid) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/find-subtasks?projectId=${projectid}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_ALLTASK,
      alltask: result.data.data,
    });
  };
};

export const getSubByMain = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/subtask?parentTaskId=${id}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_SUBTASK_BY_MAIN,
      getSubByID: result.data.data,
    });
  };
};

export const getSubTask = (tId, pId) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/subtask?parentTaskId=${tId}&projectId=${pId}`,
      { headers: authHeader() }
    );
    dp({
      type: GET_SUBTASK,
      subtask: result.data.data,
    });
  };
};

export const deleteSubTask = (id) => {
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
          .delete(`${baseURI}/subtasks/${id}`, {
            headers: authHeader(),
          })
          .then((res) => {
            dp({
              type: DELETE_SUBTASK,
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

export const assignMainTask = (id, maintask, cb) => {
  axios
    .put(`${baseURI}/tasks?parentTaskId=${id}`, maintask, {
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

export const getUserByPro = (id) => {
  return async (dp) => {
    const result = await axios.get(`${baseURI}/project-division?id=${id}`, {
      headers: authHeader(),
    });
    dp({
      type: GET_USER_PRO_ID,
      getUserPro: result.data.data,
    });
  };
};

export const assingSubtask = (subTask, cb) => {
  axios
    .post(`${baseURI}/subtasks-all-insert`, subTask, { headers: authHeader() })
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

export const updateTaskByUser = (id, subTask, cb) => {
  axios
    .put(`${baseURI}/subtasks/${id}`, subTask, {
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

export const updateTaskBymentor = (id, subTaskMen, cb) => {
  axios
    .put(`${baseURI}/subTask/${id}`, subTaskMen, {
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

export const resolvedTask = (id, tt, cb) => {
  Swal.fire({
    title: "Are you sure to resolve this issue?",
    text: "You won't be able to revert this!",
    showCancelButton: true,
    confirmButtonColor: "#3399ff",
    cancelButtonColor: "#e55353",
    confirmButtonText: "Yes, Resolve it!",
  }).then((result) => {
    if (result.value) {
      axios
        .put(`${baseURI}/subTask-resolve-issue/${id}`, tt, {
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
    }
  });
};

export const postDailyReport = (dailyReport, cb) => {
  axios
    .post(`${baseURI}/daily-reports`, dailyReport, { headers: authHeader() })
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

export const getIssseByMain = (id) => {
  return async (dp) => {
    const result = await axios.get(
      `${baseURI}/find-issue-by-parent-task/${id}`,
      {
        headers: authHeader(),
      }
    );
    dp({
      type: GET_ISSUE_BY_MAIN,
      getIssueMain: result.data.data,
    });
  };
};
