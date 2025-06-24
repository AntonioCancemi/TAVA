whatsbot/
├── backend/
│   ├── common/            # Config, error handler, DTO, mapper, utils
│   ├── auth/              # JWT, login, register, tenant
│   ├── user/              # Ruoli, permessi, collaboratori
│   ├── bot/               # Engine, flow manager, templates
│   ├── booking/           # Calendario, prenotazioni, fasce orarie
│   ├── dashboard/         # Statistiche, metriche, logs
│   ├── ai/                # AI intent handler, context engine, suggestions
│   ├── wizard/            # Setup iniziale multistep
│   └── landing/           # Sito vetrina pubblico
├── frontend/
│   ├── features/
│   │   ├── auth/
│   │   ├── bot/
│   │   ├── booking/
│   │   ├── dashboard/
│   │   ├── wizard/
│   │   ├── ai/
│   │   └── landing/
│   ├── shared/            # Componenti comuni
│   ├── app/               # Entrypoint + router
│   └── pages/             # Routing page-level
