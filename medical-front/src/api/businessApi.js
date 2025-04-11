import Request from '@/utils/request.js';


export const doctorPage = data => {
    return Request({
        url: '/doctorInfo/page',
        method: 'post',
        data: data
    })
}

export const doctorOneGet = id => {
    return Request({
        url: '/doctorInfo/one/' + id,
        method: 'get'
    })
}

export const departmentPage = data => {
    return Request({
        url: '/departmentInfo/page',
        method: 'post',
        data: data
    })
}
export const departmentListGet = data => {
    return Request({
        url: '/departmentInfo/list',
        method: 'post',
        data: data
    })
}

export const hosListGet = data => {
    return Request({
        url: '/hospitalInfo/list',
        method: 'post',
        data: data
    })
}

export const postPageGet = data => {
    return Request({
        url: '/post/page',
        method: 'post',
        data: data
    })
}

export const postOneGet = id => {
    return Request({
        url: '/post/one/' + id,
        method: 'get',
    })
}

export const registerSave = data => {
    return Request({
        url: '/patientRegistration/doReg',
        method: 'post',
        data: data
    })
}

export const myRegisterGet = () => {
    return Request({
        url: '/patientRegistration/patient/regList',
        method: 'get',
    })
}

export const patientResumeList = () => {
    return Request({
        url: '/patientResume/list',
        method: 'get',
    })
}

export const patientResumeAdd = data => {
    return Request({
        url: '/patientResume/save',
        method: 'post',
        data: data
    })
}

export const patientResumeEdit = data => {
    return Request({
        url: '/patientResume/update',
        method: 'post',
        data: data
    })
}

export const patientResumeDelete = id => {
    return Request({
        url: '/patientResume/delete/' + id,
        method: 'get',
    })
}

export const userRateListGet = id => {
    return Request({
        url: '/doctorInfo/rate/list',
        method: 'get',
    })
}

export const userRateAdd = data => {
    return Request({
        url: '/doctorInfo/rate/add',
        method: 'post',
        data: data
    })
}

