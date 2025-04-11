export function calculateAge(idCardNumber) {

    if (!idCardNumber) return

    // 获取出生日期字符串
    let birthDateStr;
    if (idCardNumber.length === 15) {
        birthDateStr = '19' + idCardNumber.substr(6, 6);
    } else if (idCardNumber.length === 18) {
        birthDateStr = idCardNumber.substr(6, 8);
    } else {
        throw new Error('无效的身份证号码');
    }

    // 解析出生日期
    let year = parseInt(birthDateStr.substr(0, 4), 10);
    let month = parseInt(birthDateStr.substr(4, 2), 10);
    let day = parseInt(birthDateStr.substr(6, 2), 10);

    // 获取当前日期
    let currentDate = new Date();
    let currentYear = currentDate.getFullYear();
    let currentMonth = currentDate.getMonth() + 1;
    let currentDay = currentDate.getDate();

    // 计算年龄
    let age = currentYear - year;
    if (currentMonth < month || (currentMonth === month && currentDay < day)) {
        age--;
    }

    return age;
}

