// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d1_1 d1_2 d1_3 d1_4);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (d2_1 d2_2 d2_3 d2_4);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x1_1 x1_2 x1_3 x1_4);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x3_1 x3_2 x3_3);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =3;
pos_y =5;
relevance = 7.0;
purpose = "";
min = 0;
max = 1;
precision = 2;
}

// Links of the associated graph:

link D1 D2;

link D1 V0;

link D1 X2;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X2;

link X2 X1;

link X3 X2;

link X4 D2;

link X4 X6;

link X5 D2;

link X5 X4;

link X6 D2;

link X6 X1;

link X7 D1;

link X7 X4;

link X8 D1;

link X8 X7;

link X9 D1;

link X9 X7;

//Network Relationships: 

relation X7 X9 X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.41968191916990927 0.6752606929170948 1.0 0.0 0.2074374812865231 0.0 0.5803180808300907 0.3247393070829051 0.0 1.0 0.7925625187134769 1.0 );
}

relation X8 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 1.0 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8566910286877558 0.1433089713122442 );
}

relation X4 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48916404251269463 1.0 0.2049347628278304 1.0 0.0 0.9824068347677108 0.0 0.0 0.5824478178938822 0.0 0.0 0.7379930756107391 1.0 1.0 0.0 1.0 0.722963357585545 0.5650587435103083 0.9475926333276997 0.5829346565137243 0.1092750663871214 0.12610138035777593 0.6842850446917336 0.11364746761294964 0.5108359574873054 0.0 0.7950652371721697 0.0 1.0 0.01759316523228912 1.0 1.0 0.41755218210611783 1.0 1.0 0.2620069243892609 0.0 0.0 1.0 0.0 0.2770366424144551 0.43494125648969173 0.05240736667230023 0.4170653434862757 0.8907249336128786 0.873898619642224 0.31571495530826627 0.8863525323870504 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6567286449155261 0.0 0.34327135508447393 );
}

relation X6 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.45821744921643187 0.9798711442238318 0.0 0.0 0.7202985089974395 0.14973912875075746 1.0 0.6597801995094646 0.5417825507835681 0.020128855776168204 1.0 0.0 0.27970149100256037 0.8502608712492425 0.0 0.3402198004905353 );
}

relation X1 X6 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3515114330028059 0.35089604261701557 0.0 0.45628882699639645 0.426811974721458 0.16546619754904768 0.2533029404577951 0.0914817164337529 0.0 0.42999621696954915 0.13054065302964463 0.4522294565698507 0.22167659227573613 0.05364154286438758 0.6161564065125602 0.0 );
}

relation X2 D2 X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 1.0 0.35952387919098394 0.0 0.27666403550488455 0.7384659960697016 0.863488695545886 0.019212997762780928 0.4439673029295148 0.9566696644276793 0.8513008135955403 0.2712132177681982 0.0 0.0 1.0 0.8784000131358841 0.9491430439355923 0.3036946218262655 0.6267361135294586 0.5914995643209193 0.4624595653334324 0.0 1.0 0.6629448176421049 0.13033404791253314 0.5854640624504228 1.0 0.7303838593753746 0.4984272614182069 0.5799111639812705 0.0 1.0 0.01125015554870224 1.0 0.0 0.23627134067932992 0.16265138227843998 0.9678378549575497 0.11894176907728057 1.0 1.0 0.9030417179310589 0.28466416044351 0.6284579540796751 0.4349336371599372 0.5975508192161267 0.0 0.0 1.0 0.0 0.6404761208090161 1.0 0.7233359644951155 0.26153400393029846 0.1365113044541139 0.980787002237219 0.5560326970704852 0.04333033557232071 0.14869918640445967 0.7287867822318019 0.0 1.0 0.0 0.12159998686411583 0.05085695606440764 0.6963053781737345 0.37326388647054143 0.40850043567908073 0.5375404346665676 1.0 0.0 0.3370551823578951 0.8696659520874669 0.4145359375495772 0.0 0.26961614062462536 0.5015727385817931 0.4200888360187295 0.0 0.0 0.9887498444512978 0.0 1.0 0.76372865932067 0.83734861772156 0.032162145042450345 0.8810582309227194 0.0 0.0 0.09695828206894111 0.71533583955649 0.3715420459203248 0.5650663628400627 0.40244918078387315 1.0 1.0 );
}

relation X3 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9929135456682232 0.0 0.0 0.8037799160598709 0.007086454331776867 0.6130427677746166 0.5441266261873099 0.1962200839401291 0.0 0.3869572322253834 0.45587337381269016 0.0 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (0.0 0.0 37.15072962612442 0.0 0.0 129.9578571908658 61.580339897621386 78.55331400741754 133.08156846553607 121.36343923793399 52.985921944689544 69.9588960544857 0.0 110.60367788060441 42.22616058735996 0.0 );
}

}
