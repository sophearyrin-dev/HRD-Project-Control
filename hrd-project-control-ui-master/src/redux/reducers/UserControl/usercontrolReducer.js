import {
  GET_USER,
  DELETE_USER,
  GET_USER_ONE
} from "../../actions/UserControl/usercontrolActionTypes";

const initialState = {
  user: [],
  userOne: [],
};

export const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_USER:
      return {
        ...state,
        user: action.user,
      };

    case GET_USER_ONE:
      return {
        ...state,
        userOne: action.userOne,
      };


    case DELETE_USER:
      return {
        ...state,
        user: state.user.filter(
          (c) => c.userId !== action.payLoad
        ),
      };
    default:
      return state;
  }
};