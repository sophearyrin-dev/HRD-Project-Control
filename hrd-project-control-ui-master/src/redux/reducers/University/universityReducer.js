import {
  GET_UNIVERSITY,
  DELETE_UNIVERSITY
} from "../../actions/University/universityActionTypes";
const initialState = {
  university: [],
};

export const universityReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_UNIVERSITY:
      return {
        ...state,
        university: action.university,
      };
    case DELETE_UNIVERSITY:
      return {
        ...state,
        university: state.university.filter(
          (c) => c.universityId !== action.payLoad
        ),
      };
    default:
      return state;
  }
};