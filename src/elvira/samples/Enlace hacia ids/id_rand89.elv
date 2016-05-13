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
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (x9_1 x9_2 x9_3 x9_4 x9_5);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x10_1 x10_2 x10_3);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (x11_1 x11_2 x11_3 x11_4 x11_5);
}

node X12(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x12_1 x12_2 x12_3);
}

node X13(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x13_1 x13_2 x13_3);
}

node X14(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x14_1 x14_2 x14_3 x14_4);
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
pos_x =3;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x6_1 x6_2);
}

node X7(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x7_1 x7_2 x7_3);
}

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node D2(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =5;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d2_1 d2_2 d2_3);
}

node X1(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 5;
states = (x1_1 x1_2 x1_3 x1_4 x1_5);
}

node X2(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x2_1 x2_2 x2_3);
}

node X3(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =6;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x3_1 x3_2 x3_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =7;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 4;
states = (x4_1 x4_2 x4_3 x4_4);
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

link D1 X3;

link D1 X5;

link D2 V0;

link D2 X1;

link D2 X2;

link D2 X3;

link D2 X4;

link X10 D1;

link X10 X7;

link X11 D1;

link X11 X12;

link X12 D1;

link X12 X10;

link X13 X14;

link X14 X10;

link X14 X11;

link X14 X6;

link X4 X2;

link X5 D2;

link X5 X8;

link X6 D2;

link X7 D2;

link X7 X5;

link X7 X8;

link X8 X6;

link X9 D1;

link X9 X11;

link X9 X13;

//Network Relationships: 

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.0 0.0 1.0 0.0 );
}

relation X10 X14 X12 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.09896364733360266 0.6156652893518869 0.0 0.0 0.0 0.0 0.0 0.27118481644310477 0.17092543753866477 0.6038618694539779 0.08567821695181409 0.3442631888105005 0.0 0.006103483624337345 0.11325751989704477 0.3606659996260722 0.9982574284693155 0.12997157633978287 0.20382979015214858 0.682275055043084 0.8251591627772359 0.19251114744547854 0.370764954617886 0.347667160251965 0.9010363526663974 0.37823122702377576 0.8867424801029552 0.6393340003739277 0.0017425715306844142 0.8700284236602172 0.7961702098478514 0.04654012851381128 0.003915399684099376 0.20362698310054342 0.5435568284302998 0.3080696509375346 );
}

relation X11 X9 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0469379999745414 0.43975767197689164 0.0 0.15631845653945528 0.01418745929610959 0.0 0.0909803925198317 0.0 0.14635492074670023 0.0 0.26465238942838804 0.07314644775633798 0.43609289693219844 0.4332309780819061 0.0 0.34619514909870264 0.4792473252921834 0.09485751266450053 0.01017151106554123 0.014372491665846268 0.07333766367690209 0.0 0.0 0.18782013816857482 0.8879506968148719 0.0 0.06807829513274873 0.3666230702102182 0.5144360778817239 0.5538168425261698 0.3031649156069599 0.0 0.1370180435623092 0.5365565858129696 0.3415249446086126 0.5415655282632598 0.13457919820460315 0.025960131109111086 0.0 0.024068479777321752 0.2054039513652853 0.5482008269360238 0.49397850940435967 0.002653197414999377 4.566910404590147E-6 0.0 0.21056998029232094 0.4531073647763315 0.28844774122024897 0.3028859700947156 0.04743393035461065 0.6247633783656981 0.0 0.0 0.1754241154541579 0.0 0.0 0.19441336137004617 0.6538198959247697 0.3900330934345665 0.37546353005827726 0.0 0.5060214905956403 0.010257786946008504 1.0668429543477511E-4 0.7747791311088129 0.26246806546649215 0.10585506554186858 0.050761260151326974 0.1432971873791147 0.38474876461004154 0.0 0.4151718738921433 0.0 0.08620846830487826 0.0 0.0 0.3116040070679118 0.12186287643376377 0.311193159223979 0.2988568549249939 0.012041501087084499 0.0 0.642950420930962 0.0977505926831791 0.22522086889118706 0.36790326658860645 0.07441449947158171 0.0 0.0 0.0 0.302090173877964 0.011717185613349156 0.030212436105124203 0.3968424716323512 0.11223932263803765 0.38617347650321343 0.3731649877884303 0.21414571657592532 0.2603327758982864 );
}

relation X12 X11 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.47474849244629125 0.33839652334967624 0.8897821589432791 0.0216494938040571 1.0 0.5132599350239845 0.0 0.0 0.9783505061959429 0.0 0.011991572529724404 0.6616034766503238 0.11021784105672092 0.0 );
}

relation X13 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.01374749451161601 0.7400595168722166 0.0 0.6528200283604393 0.0 0.019562606478765975 0.2599404831277834 1.0 0.3318764155261118 0.0 0.966689899009618 0.0 0.0 0.01530355611344898 1.0 );
}

relation X14 X13 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3606269209020175 0.39552417514057436 0.5499865021645644 0.03185648406839903 0.6009120851017651 0.0 0.0026800554784900013 4.8361934897423576E-4 0.0 0.6048365395510936 0.0030801204086864362 0.45001349783543554 );
}

relation X5 X7 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.12962292837684972 1.0 0.05375137009045604 0.6785331307777351 0.016148024309870095 0.9703077059622592 0.7488169667053489 1.0 0.0 0.19337589192648208 0.14887516539109397 0.7878894309929606 0.8703770716231503 0.0 0.946248629909544 0.3214668692222649 0.9838519756901299 0.0296922940377408 0.2511830332946511 0.0 1.0 0.8066241080735179 0.8511248346089061 0.21211056900703937 );
}

relation X6 X8 X14 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5491397116273301 0.43987166576056086 1.0 0.21012035567911494 0.48230494614791736 0.5231619893465863 1.0 0.914569473538726 0.6898387247524781 0.0 0.1282341227034247 0.8338916508166457 0.45086028837267 0.5601283342394391 0.0 0.7898796443208851 0.5176950538520826 0.4768380106534137 0.0 0.08543052646127397 0.3101612752475219 1.0 0.8717658772965753 0.16610834918335432 );
}

relation X7 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.5322191288743316 0.049426916029256675 0.0 0.4677808711256683 0.4774819771413125 1.0 0.0 0.47309110682943073 );
}

relation X8 X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3819269423938484 0.08410713659199673 0.5003928371123612 0.0 0.26510062580877297 1.0 0.21010100076799348 0.722206951644745 0.49960716288763884 0.7691059743413873 0.0 0.0 0.4079720568381582 0.19368591176325833 0.0 0.23089402565861267 0.734899374191227 0.0 );
}

relation X1 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.17441828945024757 0.4843473509082062 0.0 0.049974801949606594 0.0 0.9751418881117461 0.03234305106315601 0.05506001916454047 0.012017898482908356 0.0018875540135918011 0.13855526608889943 0.0 0.741376303523398 0.32203736383835385 0.012840213405345564 );
}

relation X2 X4 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.3223992667170607 0.5414534739131398 0.649743229926661 0.09013278084282605 0.321848183899605 0.686620655092032 0.07530987206477752 0.5518180433857938 0.0 0.41545513513217014 0.08464783921156145 0.21934785273896948 0.4304419614096802 0.2743649123714786 0.0 0.6163484549947271 0.2200889537396146 0.24326635688227313 0.524027038196778 0.23200861667788047 1.0 0.19581800256695353 0.9153521607884386 0.5111538599556982 0.2471587718732592 0.18418161371538166 0.350256770073339 0.2935187641624469 0.45806286236078037 0.07011298802569493 0.4006630897384445 0.21617333993632576 0.0 0.38872686230087644 0.0 0.26949828730533215 );
}

relation X3 D2 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.297282042920265 0.0 0.43591850263728893 0.38448152278263 0.0 0.0 0.4416489110774086 0.3390453633165918 0.2670441897873919 0.37932147626777485 0.11578818608196202 0.22759764361217907 0.4925939384286473 0.192710138583758 0.5640814973627111 0.0 1.0 1.0 0.0 0.6609546366834081 0.732955810212608 0.3727491438888919 0.48025430420405923 0.0 0.2101240186510878 0.807289861416242 0.0 0.6155184772173701 0.0 0.0 0.5583510889225914 0.0 0.0 0.2479293798433333 0.4039575097139787 0.772402356387821 );
}

relation X4 D2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.0 0.9958658251012322 0.19204491426851836 1.0 0.0 0.0 0.0 0.0 0.8076526119815488 0.0 0.004134174898767758 3.0247374993281836E-4 );
}

relation V0 D1 D2 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (143.74177207761593 148.559071669327 179.00645182928386 53.99143118659416 58.80873077830523 89.25611093826208 0.0 106.3044233815836 136.75180354154045 116.06712766936587 0.0 151.33180742103377 );
}

}