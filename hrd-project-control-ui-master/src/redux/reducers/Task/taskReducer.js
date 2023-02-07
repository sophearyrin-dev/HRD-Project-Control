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

const initialState = {
  task: [],
  taskview: [],
  mytask: [],
  alltask: [],
  subtask: [],
  getSubByMain: [],
  getUserPro: [],
  getIssueMain: [],
};

export const taskReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_TASK:
      return {
        ...state,
        task: action.task,
      };
    case GET_TASKVIEW:
      return {
        ...state,
        taskview: action.taskview,
      };
    case GET_MYTASK:
      return {
        ...state,
        mytask: action.mytask,
      };
    case GET_ALLTASK:
      return {
        ...state,
        alltask: action.alltask,
      };
    case GET_SUBTASK:
      return {
        ...state,
        subtask: action.subtask,
      };
    case GET_SUBTASK_BY_MAIN:
      return {
        ...state,
        getSubByID: action.getSubByID,
      };
    case DELETE_SUBTASK:
      return {
        ...state,
        getSubByMain: state.getSubByMain.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };
    case GET_USER_PRO_ID:
      return {
        ...state,
        getUserPro: action.getUserPro,
      };
    case GET_ISSUE_BY_MAIN:
      return {
        ...state,
        getIssueMain: action.getIssueMain,
      };
    default:
      return state;
  }
};
