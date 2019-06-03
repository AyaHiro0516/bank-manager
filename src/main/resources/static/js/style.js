// 鼠标悬停提示
tippy.setDefaults({
    animation: 'perspective',
    placement: 'top',
    arrow: 'true',
    arrowType: 'sharp',
    theme: 'google',
    inertia: 'true',
    size: 'regular'
});

tippy('.display_type', {
    content: 'choose the account type you want to show',
    placement: 'bottom'
});
tippy('.download_management', {
    content: 'download account information for all selected types',
    placement: 'bottom'
});

tippy('.userName_in', {
    content: 'start with a letter, 5-16 in length. (only [a-z] & [0-9])'
});
tippy('.password_in', {
    content: 'start with a letter, 5-16 in length. (only [a-z] & [0-9])'
});
tippy('.personId_in', {
    content: 'please enter the 18-digit ID number'
});
const inside = document.getElementById("name").value;
tippy('.fromName_in', {
    content: "please input: [ " + inside + " ], or the system will go wrong!"
});
tippy('.amount_in', {
    content: "please enter a positive floating point number."
});
tippy('.toName_in', {
    content: "ignore it except the mode is [ Transfer ]."
});
