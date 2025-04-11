import Request from '@/utils/request.js';


export const userLogin = data => {
    return Request({
        url: '/userAccount/login',
        method: 'post',
        data: data
    })
}

export const userRegister = data => {
    return Request({
        url: '/userAccount/register',
        method: 'post',
        data: data
    })
}

export const userMe = () => {
    return Request({
        url: '/userAccount/me',
        method: 'get',
    })
}
