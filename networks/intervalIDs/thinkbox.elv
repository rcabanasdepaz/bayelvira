// Influence Diagram
//   Elvira format 

idiagram  "Unknown" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = (absent , present);

HR_Propagate_Auto="0";
HR_Propagate_AutoNormal="1";
HR_Groups_GroupNames="";
HR_Font_Name="Arial";
HR_Grid_Y="10";
HR_Monitor_InitStates="5";
HR_Grid_X="10";
HR_Compile_Compress="0";
HR_Monitor_OpenGraph="0";
HR_Compile_ApproxEpsilon="0.00001";
HR_Font_Italic="0";
HR_Monitor_GraphPrecision="100";
HR_Color_DiscreteChance="16";
HR_Compile_Approximate="0";
HR_Font_Weight="400";
HR_Grid_GridSnap="1";
HR_Grid_GridShow="0";
HR_Color_Decision="17";
HR_Font_Size="-12";
HR_Color_Utility="36";
HR_Color_DescreteChance="16";
HR_Propagate_AutoSum="1";
HR_Compile_TriangMethod="0";
HR_Groups_UserGroupsNo="0";
HR_Monitor_AutoUpdGraph="0";
HR_Color_ContinuosChance="48";
HR_Monitor_InitSD="2";
HR_Groups_GroupColors="";
// Variables 

node AD(finite-states) {
title = "Arne's dice";
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =160;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (1 2);
}

node BD(finite-states) {
title = "Bente's dice";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =670;
pos_y =160;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (1 2);
}

node AB1(finite-states) {
title = "AB1";
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =160;
pos_y =310;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (1 2 "2*1" "2*2");
}

node BB1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =520;
pos_y =310;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (2 "2*1" "2*2" "call");
}

node U4(continuous) {
title = "U1U4";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =320;
pos_y =350;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node AB2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
pos_y =160;
relevance = 7.0;
purpose = "";
num-states = 3;
states = ("2*1" "2*2" "call");
}

node BB2(finite-states) {
title = "BB2";
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =520;
pos_y =160;
relevance = 7.0;
purpose = "";
num-states = 2;
states = ("2*2" "call");
}

node U5(continuous) {
title = "U5";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =280;
pos_y =270;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node U6(continuous) {
title = "U2U6";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =280;
pos_y =120;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

node AB3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =150;
relevance = 7.0;
purpose = "";
num-states = 1;
states = ("call");
}

node U7(continuous) {
title = "U7";
kind-of-node = utility;
type-of-variable = continuous;
pos_x =360;
pos_y =60;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link AB1 BB1;

link AB1 U4;

link AB2 BB2;

link AB2 U5;

link AB2 U6;

link AB3 U7;

link AD AB1;

link AD AB2;

link AD AB3;

link AD U4;

link AD U5;

link AD U6;

link AD U7;

link BB1 AB2;

link BB1 U4;

link BB1 U5;

link BB2 AB3;

link BB2 U6;

link BB2 U7;

link BD BB1;

link BD BB2;

link BD U4;

link BD U5;

link BD U6;

link BD U7;

//Network Relationships: 

relation AD { 
comment = "new";
deterministic=false;
values= table (0.5 0.5 );
}

relation BD { 
comment = "new";
deterministic=false;
values= table (0.5 0.5 );
}

relation AB1 AD { 
comment = "new";
deterministic=false;
values= table (1.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 );
}

relation U4 BD AD AB1 BB1 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 0.0 -10.0 0.0 -10.0 0.0 -10.0 -10.0 0.0 0.0 -10.0 0.0 -10.0 0.0 -10.0 -10.0 0.0 0.0 -10.0 0.0 -10.0 0.0 -10.0 -10.0 0.0 0.0 -10.0 0.0 -10.0 0.0 -10.0 -10.0 0.0 -1.0 0.0 1.0 -10.0 -1.0 -10.0 1.0 0.0 -1.0 0.0 -1.0 -10.0 1.0 -10.0 1.0 0.0 -1.0 0.0 -1.0 -10.0 1.0 -10.0 1.0 0.0 1.0 0.0 -1.0 -10.0 1.0 -10.0 -1.0 );
}

relation AB2 BB1 AD { 
comment = "new";
deterministic=false;
values= table (0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.0 1.0 1.0 1.0 1.0 1.0 1.0 );
}

relation U5 AD BD BB1 AB2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 -1.0 0.0 0.0 -1.0 0.0 0.0 1.0 0.0 0.0 -1.0 0.0 0.0 1.0 0.0 0.0 -1.0 0.0 0.0 1.0 0.0 0.0 1.0 0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 -1.0 0.0 0.0 0.0 0.0 0.0 -1.0 0.0 0.0 0.0 0.0 0.0 -1.0 0.0 0.0 0.0 );
}

relation U6 BD AD AB2 BB2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 -10.0 -10.0 0.0 -10.0 -10.0 0.0 -10.0 -10.0 0.0 -10.0 -10.0 -1.0 1.0 0.0 -1.0 1.0 0.0 1.0 1.0 0.0 1.0 -1.0 0.0 );
}

relation AB3 BB2 AD { 
comment = "new";
deterministic=false;
values= table (1.0 1.0 1.0 1.0 );
}

relation U7 BD AD BB2 AB3 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (-1.0 -1.0 -1.0 -1.0 0.0 0.0 0.0 0.0 );
}

}
