package com.example.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//アノテーションが使える場所を指定　メソッドトフィールド
@Target({ElementType.METHOD,ElementType.FIELD})
//アノテーションの保持期間　いつでもで設定
@Retention(RetentionPolicy.RUNTIME)
//制約☑　☑するクラスを実装
@Constraint(validatedBy = UniqueLoginValidator.class)
//インターフェースのアノテーション　UniqueLoginというアノテーションが利用可能
public @interface UniqueLogin {

	String message() default "このユーザはすでにいます";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
	
}
