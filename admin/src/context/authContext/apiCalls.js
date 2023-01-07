import axios from "axios";
import { loginFailure, loginStart, loginSuccess } from "./authActions"


export const login =  async (user, dispatch) => {
    dispatch(loginStart);
    try {
        const res = await axios.post('/tokens/admin', user);
        const token = res.data; 
        const config = {
            headers : {Authorization:`Bearer ${token}`}
          }
        const {data} = await axios.get('/users/me', config);
        dispatch(loginSuccess, {...data, token});
        localStorage.setItem("netflixUser", JSON.stringify({...data, token}))
        
    } catch (err) {
        dispatch(loginFailure);
    }
}