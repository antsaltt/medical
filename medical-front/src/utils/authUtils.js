
export function clearLocalData() {
    localStorage.removeItem('TOKEN')
    localStorage.removeItem('USER_INFO')
    localStorage.removeItem('USER_MENU')
}

export function clearPortalLocalData() {
    localStorage.removeItem('PORTAL_TOKEN')
    localStorage.removeItem('PORTAL_USER_INFO')
}
