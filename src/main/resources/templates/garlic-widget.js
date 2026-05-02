class GarlicWidget
{
    constructor()
    {
        this.params = new URLSearchParams(window.location.search);
    }

    getParam(name, fallback = null)
     {
        return this.params.has(name) ? this.params.get(name) : fallback;
    }

    getConfig()
     {
        const config = {};
        for (const [key, value] of this.params)
        {
            config[key] = value;
        }
        return config;
    }
}

