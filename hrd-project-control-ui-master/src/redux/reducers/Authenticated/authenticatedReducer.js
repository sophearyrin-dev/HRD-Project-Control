import {GET_UNAUTHENTICATED} from '../../actions/Authenticated/authenticatedType';
const initialState={
    authenticated:true,
};
export const authenticatedReducer = (state = initialState, action) => {
    switch (action.type) {
          case GET_UNAUTHENTICATED:
            return {
              ...state,
              authenticated:action.payLoad
            };
    
        default:
          return state;
      }
}
