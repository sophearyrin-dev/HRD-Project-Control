import { GET_COURSE } from "../../actions/Course/courseAction";
const initialState = {
  course: [],
};

export const courseReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_COURSE:
      return {
        ...state,
        course: action.course,
      };
    default:
      return state;
  }
};
