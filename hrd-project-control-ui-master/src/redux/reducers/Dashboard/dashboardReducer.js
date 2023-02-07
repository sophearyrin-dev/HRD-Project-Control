import {
  GET_USER_PROJECT,
  GET_USER_ISSUE,
  GET_ALL_PROJECT,
  GET_ALL_ISSUE,
} from "../../actions/Dashboard/dashboardActionTypes";
const initialState = {
  userProject: "",
  userIssue: "",
  projectAll: "",
  projectIssue: "",
};

export const dashboardReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_USER_PROJECT:
      return {
        ...state,
        userProject: action.payload,
        check: action.check,
      };
    case GET_USER_ISSUE:
      return {
        ...state,
        userIssue: action.payload,
      };
    case GET_ALL_PROJECT:
      return {
        ...state,
        projectAll: action.payload,
      };
    case GET_ALL_ISSUE:
      return {
        ...state,
        projectIssue: action.payload,
      };
    default:
      return state;
  }
};
