package com.koreait.mvcframework.model.blood;


//공통로직을 처리하기 위한 클래스 (웹이건 응용이건 상관없이 중립적으로 처리가 가능한 수준)
public class BloodService {
	//이 메서드를 호출하는 자는 혈액형  넘기면 알아서 판단해서 결과 반환
	public String getAdvice(String blood) {
		String msg = null;
		
		//넘겨받은 혈액형에 대한 판단 결과 도출하기
		if(blood.equals("A형")){
			msg = "소심하고 꼼꼼하고 책임감이 강함";
		}else if(blood.equals("B형")){
			msg="고집쎄고 털털하다";
		}else if(blood.equals("O형")){
			msg="잘 어울리고 오지랖이다";
		}else if(blood.equals("AB형")){
			msg="선택을 왔다 갔다";
		}
		return msg;
	}
}
