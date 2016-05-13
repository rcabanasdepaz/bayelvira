// Influence Diagram
//   Elvira format 

idiagram  "Unknown" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = (absent , present);

HR_Monitor_InitStates="5";
HR_Propagate_AutoNormal="1";
HR_Grid_GridShow="0";
HR_Font_Weight="400";
HR_Monitor_OpenGraph="0";
HR_Color_Function="4";
HR_Monitor_GraphPrecision="100";
HR_Monitor_Mean_Variance="1";
HR_Color_Interface="21";
HR_Color_DiscreteChance="16";
HR_Color_Instance="0";
HR_Monitor_AutoUpdGraph="0";
HR_Monitor_Chance_Utility="1";
HR_HTML_Desc="";
HR_ToolBar_CDVT="0";
HR_Grid_Y="10";
HR_Grid_X="10";
HR_OOBN_CheckConsistency="1";
HR_Color_Decision="17";
HR_Color_ContinuousChance="48";
HR_Groups_IDs="";
HR_Groups_GroupNames="";
HR_Compile_ApproxEpsilon="1.0E-5";
HR_Font_Name="arial";
HR_Desc="";
HR_Grid_GridSnap="1";
HR_Monitor_InitSD="2";
HR_Compile_Compress="0";
HR_Compile_SaveToMemory="0";
HR_Groups_GroupColors="";
HR_Compile_MaxMinimalSeparators="100000";
HR_Compile_Approximate="0";
HR_Compile_TriangMethod="0";
HR_OOBN_FitExpand="1";
HR_Monitor_Utility_Arrange="0";
HR_Node_Label_Style="0";
HR_Propagate_Auto="1";
HR_Html_Desc="0";
HR_Groups_UserGroupsNo="0";
HR_Font_Size="-10";
HR_Color_Utility="36";
HR_Font_Italic="0";
HR_Propagate_AutoSum="1";
HR_Zoom_ZoomFactor="100";
// Variables 

node whitecellscount(finite-states) {
title = "White Cells\ncount";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =100;
pos_y =200;
relevance = 7.0;
purpose = "";
HR_LinkControlPoints="[U:(430, 120) ]";
HR_State_1="";
HR_State_0="";
HR_LinkMode="[U:2]";
HR_Desc="";
num-states = 2;
states = ("normal" "high");
}

node appendicitis(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =210;
pos_y =260;
relevance = 7.0;
purpose = "";
HR_LinkControlPoints="[U:(430, 120) ]";
HR_State_1="";
HR_State_0="";
HR_LinkMode="[U:2]";
HR_Desc="";
num-states = 2;
states = ("false" "true");
}

node fever(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =310;
pos_y =200;
relevance = 7.0;
purpose = "";
HR_LinkControlPoints="[U:(430, 120) ]";
HR_State_1="";
HR_State_0="";
HR_LinkMode="[U:2]";
HR_Desc="";
num-states = 2;
states = ("false" "true");
}

node pain(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =210;
pos_y =200;
relevance = 7.0;
purpose = "";
HR_LinkControlPoints="[U:(430, 120) ]";
HR_State_1="";
HR_State_0="";
HR_LinkMode="[U:2]";
HR_Desc="";
num-states = 2;
states = ("false" "true");
}

node U(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =430;
pos_y =260;
relevance = 7.0;
purpose = "";
HR_Desc="";
min = 0;
max = 1;
precision = 2;
}

node Operate(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =260;
pos_y =120;
relevance = 7.0;
purpose = "";
HR_LinkControlPoints="[U:(430, 120) ]";
HR_State_1="";
HR_State_0="";
HR_LinkMode="[U:2]";
HR_Desc="";
num-states = 2;
states = ("now" "wait");
}

// Links of the associated graph:

link Operate
 U
;

link appendicitis
 U
;

link appendicitis
 fever
;

link appendicitis
 pain
;

link appendicitis
 whitecellscount
;

link fever
 Operate
;

link pain
 Operate
;

//Network Relationships: 

relation whitecellscount
 appendicitis
 { 
comment = "new";
deterministic=false;
values= table (0.95 0.01 0.05 0.99 );
}

relation appendicitis
 { 
comment = "new";
deterministic=false;
values= table (0.85 0.15 );
}

relation fever
 appendicitis
 { 
comment = "new";
deterministic=false;
values= table (0.5 0.02 0.5 0.98 );
}

relation pain
 appendicitis
 { 
comment = "new";
deterministic=false;
values= table (0.4 0.05 0.6 0.95 );
}

relation U
 Operate
 appendicitis
 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (-5.0 5.0 10.0 -10.0 );
}

}
