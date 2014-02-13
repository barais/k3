/*
* generated by Xtext
*/
grammar InternalCore;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.kermeta.language.sample.cellularautomata.rules.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.kermeta.language.sample.cellularautomata.rules.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.kermeta.language.sample.cellularautomata.rules.services.CoreGrammarAccess;

}

@parser::members {

 	private CoreGrammarAccess grammarAccess;
 	
    public InternalCoreParser(TokenStream input, CoreGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Rule";	
   	}
   	
   	@Override
   	protected CoreGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleRule
entryRuleRule returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRuleRule()); }
	 iv_ruleRule=ruleRule 
	 { $current=$iv_ruleRule.current; } 
	 EOF 
;

// Rule Rule
ruleRule returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='when' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getRuleAccess().getWhenKeyword_0());
    }
	otherlv_1='value' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getRuleAccess().getValueKeyword_1());
    }
	otherlv_2='=' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getRuleAccess().getEqualsSignKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getRuleAccess().getEvaluatedValConditionalParserRuleCall_3_0()); 
	    }
		lv_evaluatedVal_3_0=ruleConditional		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRuleRule());
	        }
       		set(
       			$current, 
       			"evaluatedVal",
        		lv_evaluatedVal_3_0, 
        		"Conditional");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_4=';' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getRuleAccess().getSemicolonKeyword_4());
    }
)
;





// Entry rule entryRuleConditional
entryRuleConditional returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getConditionalRule()); }
	 iv_ruleConditional=ruleConditional 
	 { $current=$iv_ruleConditional.current; } 
	 EOF 
;

// Rule Conditional
ruleConditional returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getConditionalAccess().getOrExpressionParserRuleCall_0()); 
    }
    this_OrExpression_0=ruleOrExpression
    { 
        $current = $this_OrExpression_0.current; 
        afterParserOrEnumRuleCall();
    }

    |((
    {
        $current = forceCreateModelElement(
            grammarAccess.getConditionalAccess().getConditionalAction_1_0(),
            $current);
    }
)	otherlv_2='if' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getConditionalAccess().getIfKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getConditionalAccess().getConditionConditionalParserRuleCall_1_2_0()); 
	    }
		lv_condition_3_0=ruleConditional		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getConditionalRule());
	        }
       		set(
       			$current, 
       			"condition",
        		lv_condition_3_0, 
        		"Conditional");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_4='{' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getConditionalAccess().getLeftCurlyBracketKeyword_1_3());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getConditionalAccess().getIfTrueExpressionConditionalParserRuleCall_1_4_0()); 
	    }
		lv_ifTrueExpression_5_0=ruleConditional		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getConditionalRule());
	        }
       		set(
       			$current, 
       			"ifTrueExpression",
        		lv_ifTrueExpression_5_0, 
        		"Conditional");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_6='}' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getConditionalAccess().getRightCurlyBracketKeyword_1_5());
    }
	otherlv_7='else' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getConditionalAccess().getElseKeyword_1_6());
    }
	otherlv_8='{' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getConditionalAccess().getLeftCurlyBracketKeyword_1_7());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getConditionalAccess().getIfFalseExpressionConditionalParserRuleCall_1_8_0()); 
	    }
		lv_ifFalseExpression_9_0=ruleConditional		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getConditionalRule());
	        }
       		set(
       			$current, 
       			"ifFalseExpression",
        		lv_ifFalseExpression_9_0, 
        		"Conditional");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_10='}' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getConditionalAccess().getRightCurlyBracketKeyword_1_9());
    }
))
;





// Entry rule entryRuleOrExpression
entryRuleOrExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getOrExpressionRule()); }
	 iv_ruleOrExpression=ruleOrExpression 
	 { $current=$iv_ruleOrExpression.current; } 
	 EOF 
;

// Rule OrExpression
ruleOrExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
    }
    this_AndExpression_0=ruleAndExpression
    { 
        $current = $this_AndExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getOrExpressionAccess().getOrLeftAction_1_0(),
            $current);
    }
)	otherlv_2='|' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getOrExpressionAccess().getVerticalLineKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=ruleAndExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getOrExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"AndExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleAndExpression
entryRuleAndExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAndExpressionRule()); }
	 iv_ruleAndExpression=ruleAndExpression 
	 { $current=$iv_ruleAndExpression.current; } 
	 EOF 
;

// Rule AndExpression
ruleAndExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAndExpressionAccess().getEqualExpressionParserRuleCall_0()); 
    }
    this_EqualExpression_0=ruleEqualExpression
    { 
        $current = $this_EqualExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAndExpressionAccess().getAndLeftAction_1_0(),
            $current);
    }
)	otherlv_2='&' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAndExpressionAccess().getAmpersandKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightEqualExpressionParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=ruleEqualExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAndExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"EqualExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleEqualExpression
entryRuleEqualExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getEqualExpressionRule()); }
	 iv_ruleEqualExpression=ruleEqualExpression 
	 { $current=$iv_ruleEqualExpression.current; } 
	 EOF 
;

// Rule EqualExpression
ruleEqualExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getEqualExpressionAccess().getComparisonExpressionParserRuleCall_0()); 
    }
    this_ComparisonExpression_0=ruleComparisonExpression
    { 
        $current = $this_ComparisonExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getEqualExpressionAccess().getEqualLeftAction_1_0(),
            $current);
    }
)	otherlv_2='==' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getEqualExpressionAccess().getEqualsSignEqualsSignKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getEqualExpressionAccess().getRightComparisonExpressionParserRuleCall_1_2_0()); 
	    }
		lv_right_3_0=ruleComparisonExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getEqualExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_3_0, 
        		"ComparisonExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleComparisonExpression
entryRuleComparisonExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getComparisonExpressionRule()); }
	 iv_ruleComparisonExpression=ruleComparisonExpression 
	 { $current=$iv_ruleComparisonExpression.current; } 
	 EOF 
;

// Rule ComparisonExpression
ruleComparisonExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getComparisonExpressionAccess().getAddExpressionParserRuleCall_0()); 
    }
    this_AddExpression_0=ruleAddExpression
    { 
        $current = $this_AddExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getComparisonExpressionAccess().getGreaterLeftAction_1_0_0_0(),
            $current);
    }
)	otherlv_2='>' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getComparisonExpressionAccess().getGreaterThanSignKeyword_1_0_0_1());
    }
)
    |((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getComparisonExpressionAccess().getLowerLeftAction_1_0_1_0(),
            $current);
    }
)	otherlv_4='<' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getComparisonExpressionAccess().getLessThanSignKeyword_1_0_1_1());
    }
))(
(
		{ 
	        newCompositeNode(grammarAccess.getComparisonExpressionAccess().getRightAddExpressionParserRuleCall_1_1_0()); 
	    }
		lv_right_5_0=ruleAddExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getComparisonExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_5_0, 
        		"AddExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleAddExpression
entryRuleAddExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getAddExpressionRule()); }
	 iv_ruleAddExpression=ruleAddExpression 
	 { $current=$iv_ruleAddExpression.current; } 
	 EOF 
;

// Rule AddExpression
ruleAddExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getAddExpressionAccess().getMultExpressionParserRuleCall_0()); 
    }
    this_MultExpression_0=ruleMultExpression
    { 
        $current = $this_MultExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAddExpressionAccess().getAddLeftAction_1_0_0_0(),
            $current);
    }
)	otherlv_2='+' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getAddExpressionAccess().getPlusSignKeyword_1_0_0_1());
    }
)
    |((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getAddExpressionAccess().getMinusLeftAction_1_0_1_0(),
            $current);
    }
)	otherlv_4='-' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getAddExpressionAccess().getHyphenMinusKeyword_1_0_1_1());
    }
))(
(
		{ 
	        newCompositeNode(grammarAccess.getAddExpressionAccess().getRightMultExpressionParserRuleCall_1_1_0()); 
	    }
		lv_right_5_0=ruleMultExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getAddExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_5_0, 
        		"MultExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleMultExpression
entryRuleMultExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMultExpressionRule()); }
	 iv_ruleMultExpression=ruleMultExpression 
	 { $current=$iv_ruleMultExpression.current; } 
	 EOF 
;

// Rule MultExpression
ruleMultExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getMultExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
    }
    this_UnaryExpression_0=ruleUnaryExpression
    { 
        $current = $this_UnaryExpression_0.current; 
        afterParserOrEnumRuleCall();
    }
((((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getMultExpressionAccess().getMultLeftAction_1_0_0_0(),
            $current);
    }
)	otherlv_2='*' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getMultExpressionAccess().getAsteriskKeyword_1_0_0_1());
    }
)
    |((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getMultExpressionAccess().getDivLeftAction_1_0_1_0(),
            $current);
    }
)	otherlv_4='/' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getMultExpressionAccess().getSolidusKeyword_1_0_1_1());
    }
)
    |((
    {
        $current = forceCreateModelElementAndSet(
            grammarAccess.getMultExpressionAccess().getModLeftAction_1_0_2_0(),
            $current);
    }
)	otherlv_6='%' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getMultExpressionAccess().getPercentSignKeyword_1_0_2_1());
    }
))(
(
		{ 
	        newCompositeNode(grammarAccess.getMultExpressionAccess().getRightUnaryExpressionParserRuleCall_1_1_0()); 
	    }
		lv_right_7_0=ruleUnaryExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getMultExpressionRule());
	        }
       		set(
       			$current, 
       			"right",
        		lv_right_7_0, 
        		"UnaryExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))*)
;





// Entry rule entryRuleUnaryExpression
entryRuleUnaryExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getUnaryExpressionRule()); }
	 iv_ruleUnaryExpression=ruleUnaryExpression 
	 { $current=$iv_ruleUnaryExpression.current; } 
	 EOF 
;

// Rule UnaryExpression
ruleUnaryExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getLiteralsExpressionParserRuleCall_0()); 
    }
    this_LiteralsExpression_0=ruleLiteralsExpression
    { 
        $current = $this_LiteralsExpression_0.current; 
        afterParserOrEnumRuleCall();
    }

    |((
    {
        $current = forceCreateModelElement(
            grammarAccess.getUnaryExpressionAccess().getNotAction_1_0(),
            $current);
    }
)	otherlv_2='!' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getUnaryExpressionAccess().getExclamationMarkKeyword_1_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getTargetLiteralsExpressionParserRuleCall_1_2_0()); 
	    }
		lv_target_3_0=ruleLiteralsExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
	        }
       		set(
       			$current, 
       			"target",
        		lv_target_3_0, 
        		"LiteralsExpression");
	        afterParserOrEnumRuleCall();
	    }

)
))
    |((
    {
        $current = forceCreateModelElement(
            grammarAccess.getUnaryExpressionAccess().getUMinusAction_2_0(),
            $current);
    }
)	otherlv_5='-' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getUnaryExpressionAccess().getHyphenMinusKeyword_2_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getTargetLiteralsExpressionParserRuleCall_2_2_0()); 
	    }
		lv_target_6_0=ruleLiteralsExpression		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
	        }
       		set(
       			$current, 
       			"target",
        		lv_target_6_0, 
        		"LiteralsExpression");
	        afterParserOrEnumRuleCall();
	    }

)
)))
;





// Entry rule entryRuleLiteralsExpression
entryRuleLiteralsExpression returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLiteralsExpressionRule()); }
	 iv_ruleLiteralsExpression=ruleLiteralsExpression 
	 { $current=$iv_ruleLiteralsExpression.current; } 
	 EOF 
;

// Rule LiteralsExpression
ruleLiteralsExpression returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='(' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getLiteralsExpressionAccess().getLeftParenthesisKeyword_0_0());
    }

    { 
        newCompositeNode(grammarAccess.getLiteralsExpressionAccess().getConditionalParserRuleCall_0_1()); 
    }
    this_Conditional_1=ruleConditional
    { 
        $current = $this_Conditional_1.current; 
        afterParserOrEnumRuleCall();
    }
	otherlv_2=')' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getLiteralsExpressionAccess().getRightParenthesisKeyword_0_2());
    }
)
    |
    { 
        newCompositeNode(grammarAccess.getLiteralsExpressionAccess().getIntegerLiteralParserRuleCall_1()); 
    }
    this_IntegerLiteral_3=ruleIntegerLiteral
    { 
        $current = $this_IntegerLiteral_3.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleIntegerLiteral
entryRuleIntegerLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntegerLiteralRule()); }
	 iv_ruleIntegerLiteral=ruleIntegerLiteral 
	 { $current=$iv_ruleIntegerLiteral.current; } 
	 EOF 
;

// Rule IntegerLiteral
ruleIntegerLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{ 
	        newCompositeNode(grammarAccess.getIntegerLiteralAccess().getValEIntParserRuleCall_0()); 
	    }
		lv_val_0_0=ruleEInt		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getIntegerLiteralRule());
	        }
       		set(
       			$current, 
       			"val",
        		lv_val_0_0, 
        		"EInt");
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleEInt
entryRuleEInt returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getEIntRule()); } 
	 iv_ruleEInt=ruleEInt 
	 { $current=$iv_ruleEInt.current.getText(); }  
	 EOF 
;

// Rule EInt
ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
	kw='-' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
    }
)?    this_INT_1=RULE_INT    {
		$current.merge(this_INT_1);
    }

    { 
    newLeafNode(this_INT_1, grammarAccess.getEIntAccess().getINTTerminalRuleCall_1()); 
    }
)
    ;





RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;

