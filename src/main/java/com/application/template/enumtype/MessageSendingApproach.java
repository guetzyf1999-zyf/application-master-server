package com.application.template.enumtype;

//验证码发送途径
public enum MessageSendingApproach {
    //email发送验证码
    EMAIL("email", 1),
    //短信平台发送验证码
    SMS("phone", 2);

    private final String approachName;
    private final int index;

    MessageSendingApproach(String approachName, int index) {
         this.approachName = approachName;
         this.index = index;
    }

    public String getApproachName() {
        return approachName;
    }

    public int getIndex() {
        return index;
    }
}
