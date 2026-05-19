
export const login = async (username, password) => {

  try {
    const request = await fetch('http://localhost:8080/auth/api/auth/login', {
      method: 'POST',
      headers:{
        "Content-Type":"application/json"
      },
      body: JSON.stringify({
        username,
        password
      })
    });

    const responseBody = await request.json();
    return { ...responseBody, success: true }
  } catch (error) {
    return {error, success: false}
  }

}

export const logout = () => {

}