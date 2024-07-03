
export function formatDate(date){    // 2024-06-20T09:28:51.693696
    var result = date.replace('T', ' ');    // T를 공백으로 변환
    var index = result.lastIndexOf(':');    // 초 앞에 있는 : 위치값, ' '은 yyy-MM-dd만 남김

    result = result.substr(0, index);   // 초 뒤로 삭제

    return result;
}