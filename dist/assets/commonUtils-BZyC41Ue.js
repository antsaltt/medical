const o={1:"男",2:"女"},h={1:"8:00",2:"9:00",3:"10:00",4:"11:00",5:"12:00",6:"13:00",7:"14:00",8:"15:00",9:"16:00",10:"17:00",11:"18:00"};function f(t){if(!t)return;let e;if(t.length===15)e="19"+t.substr(6,6);else if(t.length===18)e=t.substr(6,8);else throw new Error("无效的身份证号码");let a=parseInt(e.substr(0,4),10),n=parseInt(e.substr(4,2),10),c=parseInt(e.substr(6,2),10),r=new Date,g=r.getFullYear(),s=r.getMonth()+1,u=r.getDate(),l=g-a;return(s<n||s===n&&u<c)&&l--,l}export{f as c,o as g,h as r};
