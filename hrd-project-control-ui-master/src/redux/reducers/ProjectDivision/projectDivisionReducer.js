import {
  GET_PROJECTDIVISION,
  GET_PROJECTDIVISION_FEATURE,
  GET_SCHEDULE_FILTER,
} from "../../actions/ProjectDivision/projectdivisionActionTypes";

const initialState = {
  projectDivision: [],
  projectDivisionFeature: [],
  shceduleFilter: [],
};

export const projectDivisionReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_PROJECTDIVISION:
      return {
        ...state,
        projectDivision: action.projectDivision,
      };
    case GET_PROJECTDIVISION_FEATURE:
      return {
        ...state,
        projectDivisionFeature: action.projectDivisionFeature,
      };
    case GET_SCHEDULE_FILTER:
      return {
        ...state,
        shceduleFilter: action.shceduleFilter,
      };
    default:
      return state;
  }
};
