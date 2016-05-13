// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x11_1 x11_2);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x12_1 x12_2);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d1_1 d1_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x5_1 x5_2);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x8_1 x8_2);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (d2_1 d2_2);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x1_1 x1_2);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x2_1 x2_2);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x3_1 x3_2);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node V0(continuous) {
kind-of-node = utility;
type-of-variable = continuous;
pos_x =7;
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

link D1 X1;

link D1 X2;

link D1 X3;

link D1 X6;

link D2 V0;

link D2 X1;

link X1 V0;

link X10 D1;

link X10 X2;

link X11 D1;

link X11 X1;

link X11 X12;

link X11 X8;

link X12 D1;

link X12 X1;

link X2 V0;

link X3 V0;

link X4 V0;

link X4 X1;

link X4 X3;

link X5 D2;

link X5 X6;

link X5 X7;

link X6 D2;

link X6 X2;

link X7 D2;

link X7 X6;

link X7 X8;

link X7 X9;

link X8 D2;

link X8 X2;

link X9 D1;

link X9 X2;

//Network Relationships: 

relation X9 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.13974522841214151 1.0 0.8602547715878586 );
}

relation X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5847011720395895 0.41529882796041045 );
}

relation X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (1.0 0.0 );
}

relation X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7415074119034843 0.0 0.2584925880965156 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8639289763532428 0.13607102364675724 );
}

relation X6 X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3913321447763135 0.278301811466379 0.6903533625381307 0.5919801024631711 1.0 0.28889954765751097 0.008195218506139806 1.0 0.6086678552236865 0.721698188533621 0.3096466374618693 0.4080198975368289 0.0 0.7111004523424891 0.9918047814938602 0.0 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.09489395117447491 0.31911706635070775 0.9051060488255251 0.6808829336492923 );
}

relation X8 X7 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3790433457577844 1.0 0.4090460675110978 0.0 0.6209566542422156 0.0 0.5909539324889023 1.0 );
}

relation X1 D2 X11 X12 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.9298140613566561 1.0 0.801303537772734 0.9979512963555295 0.2044353267409931 0.3935720712194495 0.0 0.8994940813558577 0.03935299153747015 0.18641721114853363 0.97467079462426 0.0 0.0 1.0 0.0 0.6422128159491689 0.16945597793083816 0.30791998393770575 0.6765720896527951 0.9330958424253035 0.2066435607559719 0.8379594667443316 0.859980188215163 0.24988936011271384 0.25411046591346376 0.0 0.0 1.0 0.5755205227207646 0.45603187951777024 0.39769202385364627 0.0 0.07018593864334388 0.0 0.19869646222726603 0.0020487036444705644 0.7955646732590068 0.6064279287805505 1.0 0.10050591864414227 0.9606470084625299 0.8135827888514664 0.025329205375739988 1.0 0.0 0.0 0.0 0.35778718405083104 0.8305440220691618 0.6920800160622942 0.3234279103472049 0.06690415757469645 0.7933564392440281 0.16204053325566833 0.14001981178483705 0.7501106398872861 0.7458895340865361 1.0 1.0 0.0 0.4244794772792354 0.5439681204822298 0.6023079761463538 );
}

relation X2 X10 X6 X8 X9 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.7123537955770102 1.0 1.0 0.4246288685955605 0.9369808942660499 0.6519203675612586 0.5610383538833273 0.582281188405634 0.10731051732261315 0.3528646948461577 0.795852141065482 0.2946400545906295 0.5558814026979597 1.0 0.9517377740837417 0.46579807480520063 0.6698010589983369 0.38110405928078916 0.0 0.0 1.0 0.05043161313265995 0.0 0.5199819089238737 0.5363156009693726 1.0 0.0 0.6152847686454224 0.18145619392693937 1.0 1.0 1.0 0.2876462044229899 0.0 0.0 0.5753711314044394 0.06301910573395018 0.3480796324387414 0.4389616461166727 0.4177188115943661 0.8926894826773868 0.6471353051538423 0.20414785893451792 0.7053599454093705 0.4441185973020403 0.0 0.048262225916258276 0.5342019251947994 0.3301989410016631 0.6188959407192108 1.0 1.0 0.0 0.94956838686734 1.0 0.4800180910761262 0.4636843990306273 0.0 1.0 0.38471523135457764 0.8185438060730607 0.0 0.0 );
}

relation X3 X4 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.48748978451271613 0.5770993686530393 0.5324994117810122 0.26878870598652355 0.5125102154872839 0.42290063134696065 0.46750058821898777 0.7312112940134765 );
}

relation X4 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.8204518911398837 0.1795481088601163 );
}

relation V0 D1 D2 X1 X2 X3 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (330.1194971718447 374.04294493906025 0.0 304.3130762783369 346.04701701426893 389.97046478148445 276.31714835354563 320.2405961207611 266.2645662738358 0.0 196.53469761311248 240.45814538032798 282.19208611626 326.1155338834755 0.0 256.3856652227522 354.92972504013693 0.0 285.19985637941363 0.0 0.0 414.7806926497766 301.1273762218377 0.0 291.0747941421279 334.99824190934345 221.34492548140463 265.26837324862015 307.00231398455213 350.92576175176765 237.27244532382883 281.19589309104435 318.0894680861772 362.0129158533927 248.3595994254539 292.2830471926694 334.0169879286014 377.9404356958169 264.28711926787804 308.21056703509356 254.23453718816825 298.15798495538377 184.50466852744495 0.0 270.16205703059245 314.08550479780797 200.4321883698691 244.35563613708462 342.89969595446934 386.82314372168486 273.16982729374604 317.09327506096156 358.8272157968935 402.750663564109 0.0 0.0 279.0447650564604 322.9682128236759 209.3148963957371 253.2383441629526 294.97228489888454 338.89573266610006 0.0 269.16586400537676 );
}

}
