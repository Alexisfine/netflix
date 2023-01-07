import { createContext, useEffect, useReducer } from "react"
import authReducer from "./authReducer"

const INITIAL_STATE = {
    user : JSON.parse(localStorage.getItem("netflixUser")) || null,
    loading: false,
    error: false,
    
}

export const AuthContext = createContext(INITIAL_STATE);
export const AuthContextProvider = ({children}) => {
    const [state, dispatch] = useReducer(authReducer, INITIAL_STATE);
    useEffect(()=>{
        localStorage.setItem("netflixUser", JSON.stringify(state.user));
    },[state.user])
    return (
        <AuthContext.Provider value=
        {{user:state.user, loading:state.loading, error: state.error, dispatch}}>
            {children}</AuthContext.Provider>
    )
}