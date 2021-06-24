/**
 * 재사용성이 높은 코드는 객체로 정의할 수 있다.
	2015 ECMA Script 에서부터는 js도 class를 지원! 
 */

class Comments{
	constructor(container,msg,cwriter,cdate){
		//구성요소 생성
		this.div = document.createElement("div");
		this.t_msg = document.createElement("input"); //default는 textfield
		this.t_cwriter= document.createElement("input");
		this.t_cdate= document.createElement("input");
		
		//스타일 - 디자인 제어를 프로그래밍 언어로 시도할 경우 디자인에 대한 유지보수성이 떨어짐
		this.t_msg.style.width=60+"%";
		this.t_msg.readOnly=true;
		this.t_cwriter.style.width=20+"%";
		this.t_cwriter.readOnly=true;
		this.t_cdate.style.width=10+"%";
		this.t_cdate.readOnly=true;
		
		//값 대입
		this.t_msg.value = msg;
		this.t_cwriter.value = cwriter;
		this.t_cdate.value = cdate;

		//조립
		this.div.appendChild(this.t_msg);
		this.div.appendChild(this.t_cwriter);
		this.div.appendChild(this.t_cdate);

		//컨테이너에 부착
		container.appendChild(this.div);
	}
}