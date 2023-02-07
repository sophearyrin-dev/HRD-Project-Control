import {
  GET_PROJECT,
  DELETE_PROJECT,
  GET_PROJECT_ONE,
} from "../../../actions/ProjectDivision/Project/projectActionType";
const initialState = {
  project: [],
  projectOne: [],
};

export const projectReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_PROJECT:
      return {
        ...state,
        project: action.project,
      };

    case GET_PROJECT_ONE:
      return {
        ...state,
        projectOne: action.projectOne,
      };

    case DELETE_PROJECT:
      return {
        ...state,
        project: state.project.filter((c) => c.projectId !== action.payLoad),
      };
    default:
      return state;
  }
};
