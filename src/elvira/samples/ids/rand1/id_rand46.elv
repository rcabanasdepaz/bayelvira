// Influence Diagram
//   Elvira format 

idiagram  "random_id_rand" { 

// Network Properties

kindofgraph = "directed";
visualprecision = "0.00";
version = 1.0;
default node states = ("present" , "absent");

// Variables 

node X8(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =1;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x8_1 x8_2 x8_3);
}

node X9(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x9_1 x9_2);
}

node X10(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =1;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x10_1 x10_2);
}

node X11(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_y =3;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x11_1 x11_2 x11_3);
}

node D1(finite-states) {
kind-of-node = decision;
type-of-variable = finite-states;
pos_x =2;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (d1_1 d1_2 d1_3);
}

node X4(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =4;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x4_1 x4_2);
}

node X5(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =3;
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x5_1 x5_2 x5_3);
}

node X6(finite-states) {
kind-of-node = chance;
type-of-variable = finite-states;
pos_x =4;
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
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 2;
states = (x7_1 x7_2);
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
pos_y =2;
relevance = 7.0;
purpose = "";
num-states = 3;
states = (x1_1 x1_2 x1_3);
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
num-states = 2;
states = (x3_1 x3_2);
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

link D1 X1;

link D1 X3;

link D1 X4;

link D1 X6;

link D2 V0;

link D2 X3;

link X1 X3;

link X10 D1;

link X10 X11;

link X11 D1;

link X11 X8;

link X2 X1;

link X4 D2;

link X4 V0;

link X5 D2;

link X5 X10;

link X5 X7;

link X6 D2;

link X6 X2;

link X6 X4;

link X7 D2;

link X7 X8;

link X8 D1;

link X8 X4;

link X9 D1;

link X9 X4;

//Network Relationships: 

relation X8 X11 X7 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1473582023571027 0.7146227223076356 0.45459012277285427 0.4985990686658221 0.11484278818605836 0.20873374056049346 0.5742206639691263 0.14267250277605886 0.16897033958805213 0.1876666965089414 0.5821867333127752 0.1982751356479197 0.278421133673771 0.14270477491630554 0.37643953763909366 0.3137342348252364 0.3029704785011665 0.5929911237915868 );
}

relation X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.7496313931884385 0.2503686068115614 );
}

relation X10 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.6391618991995632 0.522710480455028 0.8057817058491347 0.3608381008004367 0.47728951954497195 0.19421829415086544 );
}

relation X11 X10 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.43593785298872406 0.054463526450107366 0.3939980158124061 0.48001490651819406 0.17006413119886984 0.46552156703169856 );
}

relation X4 D1 X6 X8 X9 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.5414253717742732 0.5455855671055034 0.2184628191668492 0.36647817131992094 0.4537813253811044 0.2226944054176896 0.18543929152659494 0.5134803350302648 0.9377963692975937 0.45913691817211344 0.593847414981348 0.47010431927034824 0.6994085266324 0.550516274330886 0.4198988854787284 0.5014677731126544 0.5275375178487882 0.4849583059568018 0.3791911016489139 0.283833579332458 0.9069709216862367 0.5237228326034205 0.47259840912313594 0.3798656021191199 0.5931283608982938 0.4404721882937535 0.4223400671453849 0.7527802496500129 0.08705403995275482 0.28929612672713917 0.36328677839632767 0.8476923911543299 0.13707441859969782 0.5175679495941486 0.7354023928027054 0.42591935412807735 0.45857462822572675 0.4544144328944967 0.7815371808331507 0.6335218286800791 0.5462186746188957 0.7773055945823104 0.814560708473405 0.48651966496973514 0.06220363070240633 0.5408630818278866 0.406152585018652 0.5298956807296518 0.3005914733675999 0.44948372566911404 0.5801011145212716 0.4985322268873454 0.4724624821512119 0.5150416940431982 0.6208088983510861 0.716166420667542 0.09302907831376327 0.4762771673965795 0.5274015908768641 0.6201343978808801 0.40687163910170604 0.5595278117062465 0.577659932854615 0.2472197503499871 0.9129459600472452 0.7107038732728608 0.6367132216036724 0.15230760884567 0.8629255814003021 0.4824320504058514 0.26459760719729475 0.5740806458719228 );
}

relation X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.20070355824411623 0.4418324180953582 0.35746402366052554 );
}

relation X6 D1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9622290741444456 0.7911165246316652 0.8756166218086016 0.03777092585555441 0.20888347536833482 0.12438337819139843 );
}

relation X7 X5 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.1369697509767314 0.388883276800094 0.5725015137588682 0.8630302490232686 0.611116723199906 0.4274984862411318 );
}

relation X1 D1 X2 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.28175433643977515 0.4769844149150222 0.3562839097075513 0.40331526861422307 0.49260939854291474 0.3127671070492194 0.1915850410379561 0.013211779175125954 0.3995438621485454 0.2953092652822687 0.3165479917026262 0.3910154630115586 0.5266606225222686 0.5098038059098519 0.2441722281439033 0.30137546610350835 0.1908426097544592 0.2962174299392221 );
}

relation X2 X6 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.16556481208129864 0.4083067347805239 0.8344351879187014 0.5916932652194762 );
}

relation X3 D1 D2 X1 { 
comment = "new";
kind-of-relation = potential;
deterministic=false;
values= table (0.9049200465157592 0.5934562646757104 0.4340447961320168 0.31106382455270226 0.8479723954547124 0.2896265495399213 0.2529551782273238 0.8839757829041966 0.6084205073836613 0.3015460569884011 0.8585800322646651 0.8287986052484042 0.31592820259424487 0.4830753369588628 0.772656705622119 0.8125220293386431 0.4407667112611785 0.3468170413908882 0.09507995348424077 0.4065437353242895 0.5659552038679833 0.6889361754472978 0.1520276045452877 0.7103734504600786 0.7470448217726762 0.11602421709580338 0.39157949261633873 0.6984539430115989 0.141419967735335 0.1712013947515959 0.6840717974057551 0.5169246630411373 0.227343294377881 0.18747797066135674 0.5592332887388215 0.6531829586091118 );
}

relation V0 D1 D2 X4 { 
comment = "new";
kind-of-relation = utility;
deterministic=false;
values= table (130.86902473067278 145.1722894093273 118.6409344785751 132.94419915722963 161.3465797935194 175.64984447217392 149.11848954142172 163.42175422007625 151.96965468226355 166.27291936091808 139.74156443016588 154.0448291088204 );
}

}
