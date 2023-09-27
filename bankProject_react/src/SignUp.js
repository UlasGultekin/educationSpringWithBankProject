import React, { Component } from "react";

 class SignUp extends Component{
    state={
        username:null,
        password:null
    }

    onChange =  event=>{
     const {name,value}=event.target

      this.setState({
        [name]:value
      })


    };

    render(){
        return(
            <form>
            <h1>Sign Up Customer</h1>
            <div>
            <label>Username</label>
            <input name="username" onChange={this.onChange}/>
            </div>
            <div>
            <label>Password</label>
            <input name="password" onChange={this.onChange} type="password"/>
            </div>
            </form>
        )
    }

}
export default SignUp;