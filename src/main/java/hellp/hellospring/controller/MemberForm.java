package hellp.hellospring.controller;

/*
       <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
       인풋 타입 텍스트의 name을 보고 스프링이 name을 넣어준다. setname을 통해서 값이 들어가게 된다.
 */
public class MemberForm {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
