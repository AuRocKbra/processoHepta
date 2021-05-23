var inicio = new Vue({
	el:"#inicio",
    data: {
        funcionarios: [],
        listaProdutosHeader: [
			{sortable: false, key: "nome", label:"Nome"},
			{sortable: false, key: "setor.nome", label:"Setor"},
			{sortable: false, key: "salario", label:"Salário"},
			{sortable: false, key: "email", label:"Email"},
			{sortable: false, key: "idade", label:"Idade"}
		]
    },
    created: function(){
        let vm =  this;
        vm.buscaProdutos();
    },
    methods:{
        buscaProdutos: function(){
			const vm = this;
			axios.get("/funcionarios")
			.then(response => {vm.funcionarios = response.data;
			}).catch(function (error) {
				vm.mostraAlertaErro("Erro interno", "Não foi possível listar funcionários");
			}).finally(function() {
			});
		},
    }
});