var inicio = new Vue({
	el:"#inicio",
    data: {
        funcionarios: [],
        listaProdutosHeader: [
        	{sortable: false, key: "id", label:"Id"},
			{sortable: false, key: "nome", label:"Nome"},
			{sortable: false, key: "setor.nome", label:"Setor"},
			{sortable: false, key: "salario", label:"Salário"},
			{sortable: false, key: "email", label:"Email"},
			{sortable: false, key: "idade", label:"Idade"}
		],
    },
    created: function(){
        let vm =  this;
        vm.listaFuncionarios();
    },
    methods:{
        listaFuncionarios: function(){
			const vm = this;
			axios.get("/funcionarios")
			.then(response => {vm.funcionarios = response.data;
			}).catch(function (error) {
				vm.mostraAlertaErro("Erro interno", "Não foi possível listar funcionários");
			}).finally(function() {
			});
		}
    }
});
var cadastro = new Vue({
	el:"#form-newFuncionario",
	data:{
		form:{
			nome:"",
			setor:{
				id:"1",
				nome:""
			},
			salario:"",
			email:"",
			idade:""
		}
	},
	methods:{
		addFuncionario(){
			const vm = inicio;
			axios.post("/funcionarios",this.form).then(response => {
				vm.listaFuncionarios(); 
			});
		},
    }
});