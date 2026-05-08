output "acr_login_server" {
  description = "URL du Container Registry"
  value       = azurerm_container_registry.acr.login_server
}

output "acr_admin_username" {
  description = "Username ACR"
  value       = azurerm_container_registry.acr.admin_username
}

output "key_vault_uri" {
  description = "URI du Key Vault"
  value       = azurerm_key_vault.kv.vault_uri
}

output "resource_group_name" {
  description = "Nom du Resource Group"
  value       = azurerm_resource_group.rg.name
}
