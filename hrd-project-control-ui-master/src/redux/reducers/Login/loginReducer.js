import { POST_USERLOGIN } from "../../actions/Login/loginActionTypes";

const initialState = {
  validToken: false,
  users: [],
};

const booleanActionPayload = (payload) => {
  if (payload) {
    return true;
  } else {
    return false;
  }
};
export default function (state = initialState, action) {
  switch (action.type) {
    case POST_USERLOGIN:
      return {
        ...state,
        validToken: booleanActionPayload(action.payload),
        users: action.payload,
      };

    default:
      return state;
  }
}
