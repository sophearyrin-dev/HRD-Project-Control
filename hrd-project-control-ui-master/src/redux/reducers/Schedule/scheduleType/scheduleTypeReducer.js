import {
  GET_PROJECTTYPE,
  DELETE_PROJECTTYPE,
} from "../../../actions/Schedule/scheduleType/scheduleTypeActionTypes";
const initialState = {
  projectType: [],
};

export const projectTypeReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_PROJECTTYPE:
      return {
        ...state,
        projectType: action.projectType,
      };
    case DELETE_PROJECTTYPE:
      return {
        ...state,
        projectType: state.projectType.filter(
          (c) => c.typeId !== action.payLoad
        ),
      };
    default:
      return state;
  }
};
